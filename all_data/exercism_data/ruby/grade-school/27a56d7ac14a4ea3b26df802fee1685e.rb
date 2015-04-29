class School
  def initialize
    @registry = {}
  end
  def to_hash
    Hash[@registry.sort]
  end
  def add (name, grade)
    if @registry.has_key?(grade)
      @registry[grade].push(name)
      @registry[grade] = @registry[grade].sort
    else
      @registry[grade] = [name]
    end
  end
  def grade (grade)
    @registry.fetch(grade, [])
  end
end
