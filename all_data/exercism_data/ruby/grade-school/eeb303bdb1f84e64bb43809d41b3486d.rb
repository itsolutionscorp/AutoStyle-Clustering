class School
  def initialize
    @grades = {}
  end

  def add(student, grade)
    @grades[grade] ||= []
    @grades[grade] << student
    self # avoid returning a reference to the array
  end

  def grade(grade)
    students = @grades[grade] || []
    students.map(&:dup).sort!
  end

  def to_hash
    @grades.keys.sort.each_with_object({}) do |grade, copy|
      copy[grade] = grade(grade)
    end
  end
end
