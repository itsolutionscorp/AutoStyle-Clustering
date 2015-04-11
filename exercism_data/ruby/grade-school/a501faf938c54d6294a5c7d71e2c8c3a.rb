class School
  def initialize
    @students = Hash.new { |_| [] }
  end

  def add(name, grade_number)
    @students[grade_number] = @students[grade_number] << name
  end

  def db
    @students
  end

  def grade(grade_number)
    @students.fetch grade_number, []
  end

  def sort
    Hash[sorted_by_name.sort]
  end

  private

  def sorted_by_name
    Hash[@students.map { |k, v| [k, v.sort] }]
  end
end
