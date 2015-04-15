class School
  def initialize
  	@school = Hash.new
  end

  def to_hash
    @school.sort.to_h
  end

  def grade(grade)
  	@school[grade] ? @school[grade] : []
  end

  def add(student,grade) # DRY this up?
  	if @school[grade]
  	  @school[grade] = @school[grade] << student
  	else
  	  @school[grade] = [student]
  	end
  	@school[grade].sort!
  end
end
