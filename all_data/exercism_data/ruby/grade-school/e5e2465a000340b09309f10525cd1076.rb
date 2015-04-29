class School
  def initialize
    @school = {}
  end

  def to_hash
    Hash[@school.sort] # Sort School by Grade
  end

  def grade(grade)
    @school[grade] || []
  end

  def add(name, grade)
    if @school.has_key?(grade)
      @school[grade] << name
      @school[grade].sort! # Sort Students
    else
      @school.merge!(grade => [name])
    end
  end
end
