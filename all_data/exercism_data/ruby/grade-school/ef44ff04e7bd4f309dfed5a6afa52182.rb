class School
  attr_accessor :grade_to_students

  def initialize
    @grade_to_students = Hash.new { |hash, key| hash[key] = [] }
  end

  def add(student, grade)
    grade_to_students[grade] << student
    grade_to_students[grade].sort!
    sort_by_grades
  end

  def grade(grade_number)
    grade_to_students[grade_number]
  end

  def to_hash
    grade_to_students
  end

  private

  def sort_by_grades
    sorted_hash = Hash.new { |hash, key| hash[key] = [] }
    grade_to_students.sort.map { |key, value| sorted_hash[key] = value }
    self.grade_to_students = sorted_hash
  end
end
