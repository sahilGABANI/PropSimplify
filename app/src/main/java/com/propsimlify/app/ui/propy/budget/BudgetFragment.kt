package com.propsimlify.app.ui.propy.budget

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.propsimlify.R
import com.propsimlify.app.base.extension.hideKeyboard
import com.propsimlify.app.base.extension.showToast
import com.propsimlify.app.utils.rengepicker.OnRangeChangedListener
import com.propsimlify.app.utils.rengepicker.RangeSeekBar
import com.propsimlify.databinding.FragmentBudgetBinding


class BudgetFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = BudgetFragment()
    }

    private var _binding: FragmentBudgetBinding? = null
    private val binding get() = _binding!!

    private var entries: java.util.ArrayList<BarEntry> = java.util.ArrayList()
    private var leftUnselectedDataSet: java.util.ArrayList<BarEntry> = java.util.ArrayList()
    private var rightUnselectedDataSet: java.util.ArrayList<BarEntry> = java.util.ArrayList()
    private var selectedDataSet: java.util.ArrayList<BarEntry> = java.util.ArrayList()
    var onRightPinChanged: ((index: Int, rightPinValue: String?) -> Unit)? = null
    var onLeftPinChanged: ((index: Int, leftPinValue: String?) -> Unit)? = null
    var onRangeChanged: ((leftPinValue: String?, rightPinValue: String?) -> Unit)? = null
    var onSelectedItemsSizeChanged: ((sizeItemsSelected: Int) -> Unit)? = null
    var onSelectedEntriesSizeChanged: ((entriesSize: Int) -> Unit)? = null

    private var oldLeftPinIndex = 0
    private var oldRightPinIndex = 0

    private var mainData: BarData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.range.setProgress(10000f, 110000f)
        var minimum = 10000f
        var maximum = 110000f
        requireActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        binding.minimumPrice.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (v.text.toString().toInt() >= 10000) {
                    minimum = v.text.toString().toFloat()
                    binding.range.setProgress(minimum, maximum)
                    binding.minimumPrice.clearFocus()
                    requireActivity().hideKeyboard(binding.minimumPrice.rootView)
                } else {
                    showToast("Minimum amount is 10000 AED.")
                    binding.minimumPrice.clearFocus()
                    requireActivity().hideKeyboard(binding.minimumPrice.rootView)
                }
                true
            } else false
        }

        binding.maximumPrice.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (v.text.toString().toInt() in 10000..110000) {
                    maximum = v.text.toString().toFloat()
                    binding.range.setProgress(minimum, maximum)
                    binding.maximumPrice.clearFocus()
                    requireActivity().hideKeyboard(binding.maximumPrice.rootView)
                } else {
                    showToast("Please enter amount between 10000 And 110000 AED.")
                    binding.minimumPrice.clearFocus()
                    requireActivity().hideKeyboard(binding.minimumPrice.rootView)
                }
                true
            } else false
        }

        binding.range.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onRangeChanged(rangeSeekBar: RangeSeekBar, leftValue: Float, rightValue: Float, isFromUser: Boolean) {
                binding.maximumPrice.setText(rightValue.toInt().toString())
                binding.minimumPrice.setText(leftValue.toInt().toString())
                val startValue = leftValue.toInt()
                val endValue = rightValue.toInt()
                val startIndex  = (startValue -10000) / 5000
                val endIndex  = (endValue -10000) / 5000
                onRangeChange(startIndex,endIndex)

            }
            override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {

            }
            override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {

            }
        })

        val barEntrys = ArrayList<BarEntry>()
        barEntrys.add(BarEntry(2.0f, 5.0f))
        barEntrys.add(BarEntry(4.0f, 4.0f))
        barEntrys.add(BarEntry(6.0f, 6.0f))
        barEntrys.add(BarEntry(8.0f, 8.0f))
        barEntrys.add(BarEntry(10.0f, 4.0f))
        barEntrys.add(BarEntry(12.0f, 9.0f))
        barEntrys.add(BarEntry(14.0f, 10.0f))
        barEntrys.add(BarEntry(16.0f, 14.0f))
        barEntrys.add(BarEntry(18.0f, 13.0f))
        barEntrys.add(BarEntry(20.0f, 10.0f))
        barEntrys.add(BarEntry(22.0f, 16.0f))
        barEntrys.add(BarEntry(24.0f, 13.0f))
        barEntrys.add(BarEntry(26.0f, 7.0f))
        barEntrys.add(BarEntry(28.0f, 11.0f))
        barEntrys.add(BarEntry(30.0f, 5.0f))
        barEntrys.add(BarEntry(32.0f, 7.0f))
        barEntrys.add(BarEntry(34.0f, 10.0f))
        barEntrys.add(BarEntry(36.0f, 11.0f))
        barEntrys.add(BarEntry(38.0f, 14.0f))
        barEntrys.add(BarEntry(40.0f, 15.0f))
        val barDataSet = BarDataSet(barEntrys, "Selected Entries")
        entries.addAll(barEntrys)
        barDataSet.color = ContextCompat.getColor(requireContext(), R.color.colorChartSelected)
        barDataSet.valueTextColor = ContextCompat.getColor(requireContext(), R.color.white)
        val data = BarData(barDataSet)
        binding.chart.data = data
        binding.chart.apply {
            legend.isEnabled = false
            description.isEnabled = false
            isDoubleTapToZoomEnabled = false
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.isGranularityEnabled = false
            xAxis.labelCount = 0
            xAxis.isEnabled = false
            axisLeft.axisMinimum = 0f
            axisRight.isEnabled = false
            axisLeft.isEnabled = false
            isClickable = false
//            data.isHighlightEnabled = false
            setDrawMarkers(false)
            setDrawGridBackground(false)

        }
        binding.chart.invalidate()

    }


    private fun onRangeChange(leftPinIndex: Int, rightPinIndex: Int) {
        leftUnselectedDataSet.clear()
        selectedDataSet.clear()
        rightUnselectedDataSet.clear()

        entries.forEachIndexed { index, item ->
            if (index <= leftPinIndex) {
                leftUnselectedDataSet.add(item)
            }

            if (index in leftPinIndex..rightPinIndex) {
                selectedDataSet.add(item)
            }

            if (index >= rightPinIndex) {
                rightUnselectedDataSet.add(item)
            }
        }

        if ((leftPinIndex >= 0 && leftPinIndex < entries.size)
            && (rightPinIndex >= 0 && rightPinIndex < entries.size)
        ) {
            val leftVal = entries[leftPinIndex].x.toInt().toString()
            val rightVal = entries[rightPinIndex].x.toInt().toString()
            onRangeChanged?.invoke(leftVal, rightVal)
        }

        if (oldLeftPinIndex != leftPinIndex) {
            if (leftPinIndex >= 0 && leftPinIndex < entries.size) {
                onLeftPinChanged?.invoke(leftPinIndex, entries[leftPinIndex].x.toString())
            }
            oldLeftPinIndex = leftPinIndex
        }
        if (oldRightPinIndex != rightPinIndex) {
            if (rightPinIndex >= 0 && rightPinIndex < entries.size) {
                onRightPinChanged?.invoke(
                    rightPinIndex,
                    entries[rightPinIndex].x.toString()
                )
            }
            oldRightPinIndex = rightPinIndex
        }

        calculateSelectedItemsSize()
        calculateSelectedEntriesSize()

        drawChart()
    }


    private fun calculateSelectedItemsSize() {
        var totalSelectedSize = 0
        selectedDataSet.forEach { entry ->
            totalSelectedSize += entry.y.toInt()
        }
        onSelectedItemsSizeChanged?.invoke(totalSelectedSize)
    }

    private fun calculateSelectedEntriesSize() {
        onSelectedEntriesSizeChanged?.invoke(selectedDataSet.size)
    }

    private fun drawChart() {
        val barDataSet = BarDataSet(selectedDataSet, "Selected Entries")
        barDataSet.color = resources.getColor(R.color.colorPrimary,null)
        barDataSet.valueTextColor = ContextCompat.getColor(requireContext(), R.color.white)

        var leftDataSet = BarDataSet(leftUnselectedDataSet, "")
        leftDataSet = styleDataSet(dataSet = leftDataSet, isSelected = false)

        var centerDataSet = BarDataSet(selectedDataSet, "")
        centerDataSet = styleDataSet(dataSet = centerDataSet, isSelected = true)

        var rightDataSet = BarDataSet(rightUnselectedDataSet, "")
        rightDataSet = styleDataSet(dataSet = rightDataSet, isSelected = false)

        mainData = BarData()
        if (leftUnselectedDataSet.isNotEmpty()) {
            mainData?.addDataSet(leftDataSet)
        }
        if (selectedDataSet.isNotEmpty()) {
            mainData?.addDataSet(centerDataSet)
        }
        if (rightUnselectedDataSet.isNotEmpty()) {
            mainData?.addDataSet(rightDataSet)
        }
        binding.chart.data = mainData
        binding.chart.invalidate()
    }



    private fun styleDataSet(dataSet: BarDataSet, isSelected: Boolean = false): BarDataSet {
        if (!isSelected) {
            dataSet.apply {
                color = resources.getColor(R.color.colorChartUnselected,null)
            }
        } else {
            dataSet.apply {
                color = resources.getColor(R.color.colorPrimary,null)
            }
        }
        dataSet.apply {
            setDrawValues(false)
        }

        return dataSet
    }

}