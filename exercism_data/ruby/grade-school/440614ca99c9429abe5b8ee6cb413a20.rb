class School


  def initialize
    @students = {}
  end

  def add(name, grade_id)
    current_grade = grade(grade_id)
    current_grade << name

    @students[grade_id] = current_grade

    sort_students
  end

  def grade(grade_id)
    students = @students[grade_id] || []
  end

  def to_hash
    @students
  end

  private
  def sort_students
    # sort by grades
    @students = @students.sort_by {|grade, studends| grade }.to_h

    # sort students in grade
    @students.each {|grade, students| students.sort!}
  end
end
