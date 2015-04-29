class School
  def initialize
    @school = {}
  end

  def to_hash
    @school
  end

  def add(name, grade)
    if @school.has_key?(grade)
      @school[grade] << name
      @school[grade].sort!
    else
      @school.merge!(grade => [name])
      @school = Hash[@school.sort]
    end
  end

  def grade(grade)
    @school[grade] || []
  end
end
