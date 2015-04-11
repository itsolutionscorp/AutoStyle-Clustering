class School
  def initialize
    @school = {}
  end

  def add(name, grade)
    @school[grade] = [] if !@school.has_key?(grade)
    @school[grade].push(name)
  end

  def to_hash
    @school.to_hash
  end

  def grade(grade)
    if !@school.has_key?(grade)
      []
    else
      @school[grade].sort
    end
  end


end
