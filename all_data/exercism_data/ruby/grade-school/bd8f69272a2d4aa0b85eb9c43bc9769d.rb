class School

  def initialize
    @school = Hash.new([])
  end

  def add(student, grade)
    school[grade] += [student]
  end

  def grade(grade)
    school[grade].sort
  end

  def to_hash
    Hash[sort_school]
  end

  private

  attr_reader :school

  def sort_school
    sort_all_grades.sort
  end

  def sort_all_grades
    school.each_with_object({}) { |(key, _), hash| hash[key] = grade(key) }
  end

end
