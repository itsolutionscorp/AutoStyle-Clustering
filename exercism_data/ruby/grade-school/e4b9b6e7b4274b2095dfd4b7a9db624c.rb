class School

  def initialize
    @school = Hash.new()
  end

  def to_hash
    @school
  end

  def add(name, grade)
    sort_grades = []
    @school[grade] ? @school[grade] << name : @school[grade] = name.split(" ")
    @school[grade].sort!
    sort_grades = @school.to_a.sort!
    @school = Hash[sort_grades]
  end

  def grade(grade)
    @school[grade] ? @school[grade] : []
  end


end
