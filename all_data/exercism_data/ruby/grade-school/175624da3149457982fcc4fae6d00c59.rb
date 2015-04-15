class School
  def initialize
    @school = {}
  end

  def add(name, grade)
   @school[grade] ? @school[grade] << name : @school[grade] = [name]
  end

  def to_hash
    @school.sort_by { |grade, name| [grade, name.sort!] }.to_h
  end

  def grade(num)
    @school[num] ? @school[num].sort : []
  end

end
