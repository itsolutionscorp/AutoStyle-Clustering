class School
  def initialize
  	@school = Hash.new { |this, grade| this[grade] = []}
  end

  def add(student,grade)
  	@school[grade].push(student).sort!
  end

  def grade(grade)
  	@school[grade]
  end

  def to_hash
    @school.sort.to_h
  end
end
