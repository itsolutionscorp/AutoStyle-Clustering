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
    Hash[school.each_with_object({}) { |(key, value), hash| hash[key] = grade(key) }.sort]
  end

  private

  attr_reader :school

end
