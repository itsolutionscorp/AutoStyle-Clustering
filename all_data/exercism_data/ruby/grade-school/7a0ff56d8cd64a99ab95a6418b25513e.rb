class School

  def initialize
    @students = {}
  end

  def db
    @students
  end

  def add(student, grade)
    if not @students.key? grade
      @students[grade] = []
    end
    @students[grade] << student
  end

  def grade(grade)
    if @students.key? grade
      @students[grade]
    else
      []
    end
  end

  def sort
    sorted = {}
    @students.keys.sort!.each do |key|
      sorted[key] = @students[key].sort
    end
    sorted
  end

end
