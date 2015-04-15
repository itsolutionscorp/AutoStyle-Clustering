class School

  def initialize()
    @students = {}
  end

  def db
    @students
  end

  def add(name, grade)
    if @students[grade]
      @students[grade] << name
    else
      @students[grade] = [name]
    end
  end

  def grade(grade)
    @students[grade] || []
  end

  def sort
    student_list = {}
    @students.each do |grade, list|
      student_list[grade] = list.sort
    end
    Hash[student_list.sort]
  end

end
