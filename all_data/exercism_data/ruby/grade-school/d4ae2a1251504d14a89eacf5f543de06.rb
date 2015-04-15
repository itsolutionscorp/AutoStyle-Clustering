class School
  def initialize
    @school = {}
  end

  def add(name, grade)
    @school[grade] ? @school[grade].push(name).sort! : @school[grade] = [name]
  end

  def to_hash
    Hash[@school.sort_by(&:first)]
  end

  def grade(grade)
    @school[grade] || []
  end
end
