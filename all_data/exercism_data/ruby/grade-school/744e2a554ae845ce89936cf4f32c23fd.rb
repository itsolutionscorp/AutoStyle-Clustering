class School
  attr_accessor :school
  private       :school=

  def initialize
    self.school = Hash.new { |hash,key| hash[key] = [] }
  end

  def add name, grade_num
    self.school[grade_num] << name
    self.school[grade_num].sort!
  end

  def grade grade_num
    school[grade_num]
  end

  def to_hash
    Hash[school.sort]
  end
end
