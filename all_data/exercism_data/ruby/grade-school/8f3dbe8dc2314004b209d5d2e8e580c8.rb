class School
  attr_accessor :school
  private       :school=

  def initialize
    self.school = {}
  end

  def add name, grade_num
    if school[grade_num]
      self.school[grade_num] << name
      self.school[grade_num].sort!
    else
      self.school[grade_num] = [name]
    end
  end

  def grade grade_num
    school[grade_num] || []
  end

  def to_hash
    Hash[school.sort]
  end
end
