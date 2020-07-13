package com.levkorol.todo.ui.schedule.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.levkorol.todo.R
import com.levkorol.todo.data.note.MainRepository
import com.levkorol.todo.model.Schedule
import com.levkorol.todo.ui.MainActivity
import com.levkorol.todo.utils.Tools
import kotlinx.android.synthetic.main.fragment_add_schedule.*
import java.text.SimpleDateFormat
import java.util.*

//class AllScheduleAdapter(
//    val activity: MainActivity
//) : RecyclerView.Adapter<AllScheduleAdapter.ScheduleViewHolder>() {
//
//    private lateinit var schedule: Schedule
//    var isEditMode = false
//    private var date: Long = 1
//
//
//    var dataItems: List<Schedule> = listOf()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
//
//    override fun getItemCount() = dataItems.size
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val view = layoutInflater.inflate(
//            R.layout.item_list_all_schedule,
//            parent, false
//        )
//        return ScheduleViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
//        val item = dataItems[position]
//
//        holder.title_schedule.text = item.description
//        holder.date_schedule.text = Tools.dateToString(item.date)
//        if (item.time == 1.toLong()) {
//            holder.time.visibility = View.GONE
//        } else {
//            holder.time.text = Tools.convertLongToTimeString(item.time)
//        }
//
//        holder.timer.visibility =
//            if (item.alarm && item.date < System.currentTimeMillis()
//                && item.time < System.currentTimeMillis()
//            ) View.VISIBLE else View.GONE
//
//
//
//        holder.editTaskBtn.setOnClickListener {
//            holder.editList.visibility = View.VISIBLE
//            holder.editTaskBtn.text = "Режим редактирования"
//            isEditMode = true
//            if (isEditMode) {
//                with(holder.title_schedule) {
//                    isFocusable = true
//                    isFocusableInTouchMode = true
//                    isLongClickable = true
//                    isCursorVisible = true
//                    holder.title_schedule.isEnabled = true
//                }
//            }
//        }
//
//        holder.exitEdit.setOnClickListener {
//            notifyDataSetChanged()
//            holder.editList.visibility = View.GONE
//            holder.editTaskBtn.text = "Редактировать"
//            isEditMode = false
//            if (!isEditMode) {
//                holder.title_schedule.isFocusable = false
//                holder.title_schedule.isFocusableInTouchMode = false
//                holder.title_schedule.isLongClickable = false
//                holder.title_schedule.isCursorVisible = false
//                holder.title_schedule.isEnabled = false
//            }
//        }
//
////        holder.timer.setOnClickListener {
////            schedule = dataItems[position]
////            val builder = MaterialAlertDialogBuilder(activity)
////            builder.setMessage("Выключить оповещение на ${holder.time.text}?")
////            builder.setPositiveButton("Да") { _, _ ->
////                schedule.alarm = false
////                MainRepository.updateSchedule(schedule)
////            }
////            builder.setNegativeButton("Отмена") { _, _ ->
////            }
////            val dialog: AlertDialog = builder.create()
////            dialog.show()
////        }
//
//        holder.saveTask.setOnClickListener {
//            holder.editList.visibility = View.GONE
//            holder.editTaskBtn.text = "Редактировать"
//            isEditMode = false
//            if (!isEditMode) {
//                holder.title_schedule.isFocusable = false
//                holder.title_schedule.isFocusableInTouchMode = false
//                holder.title_schedule.isLongClickable = false
//                holder.title_schedule.isCursorVisible = false
//                holder.title_schedule.isEnabled = false
//            }
//            schedule = dataItems[position]
//
//            schedule.description = holder.title_schedule.text.toString()
//           // schedule.dateCreate = System.currentTimeMillis()
//
//
//            MainRepository.updateSchedule(schedule)
//
//            notifyDataSetChanged()
//        }
//
//        holder.dateInEdit.text = Tools.dateToString(item.date)
//        holder.timeInEdit.text = Tools.convertLongToTimeString(item.time)
//
//
//        holder.deleteTask.setOnClickListener {
//            val builder = MaterialAlertDialogBuilder(activity)
//            builder.setMessage("Удалить: ${holder.title_schedule.text} ?")
//            builder.setPositiveButton("Да") { _, _ ->
//                MainRepository.deleteSchedule(item.id)
//            }
//            builder.setNegativeButton("Отмена") { _, _ ->
//            }
//            val dialog: AlertDialog = builder.create()
//            dialog.show()
//        }
//
//        holder.changeDate.setOnClickListener {
//            val builder = MaterialDatePicker.Builder.datePicker()
//            val picker: MaterialDatePicker<Long> = builder.build()
//            picker.addOnPositiveButtonClickListener { unixTime ->
//                holder.dateInEdit.text = SimpleDateFormat("EEEE, dd MMM, yyyy").format(Date(unixTime))
//                date = unixTime
//            }
//            picker.show(parentFragmentManager, picker.toString())
//        }
//    }
//
//
//    class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var date_schedule: TextView = itemView.findViewById(R.id.date)
//        var time: TextView = itemView.findViewById(R.id.tv_time)
//        var title_schedule: TextView = itemView.findViewById(R.id.tv_title)
//        var timer: ImageView = itemView.findViewById(R.id.iv_notify)
//        var editTaskBtn: TextView = itemView.findViewById(R.id.edit_task)
//        var editList: LinearLayout = itemView.findViewById(R.id.edit_ll)
//        var exitEdit: TextView = itemView.findViewById(R.id.exit_edit)
//        var deleteTask: TextView = itemView.findViewById(R.id.delete_task)
//        var saveTask:TextView = itemView.findViewById(R.id.save_task)
//        var dateInEdit: TextView = itemView.findViewById(R.id.date_in_edit)
//        var timeInEdit: TextView = itemView.findViewById(R.id.time_in_edit)
//        var changeDate: LinearLayout = itemView.findViewById(R.id.edit_date)
//        var changeTime: LinearLayout = itemView.findViewById(R.id.edit_time)
//    }
//}