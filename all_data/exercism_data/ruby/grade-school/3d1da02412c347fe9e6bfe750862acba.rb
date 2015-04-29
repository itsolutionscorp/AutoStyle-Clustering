class School
  def initialize name
    @eventlog = []
  end

  def add student_name, grade
    @eventlog << [grade, student_name]
  end

  def grade level, sorted_by: :time
    @eventlog.select { |grade, _| grade == level }
             .map(&:last)
  end

  def grades
    @eventlog.map(&:first).sort.uniq
  end

  def db
    export_eventlog_as_hash @eventlog
  end

  def sort
    export_eventlog_as_hash @eventlog.sort
  end

  private

  def export_eventlog_as_hash eventlog
    eventlog.each_with_object({}) do |(grade, student),out|
      out[grade] ||= []
      out[grade] << student
    end
  end
end
