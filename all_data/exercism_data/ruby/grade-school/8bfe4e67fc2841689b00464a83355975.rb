class School
  def initialize
    @school = {}
  end

  def to_hash
    @school
  end

  def add(name, grade)
    if @school[grade]
      @school[grade] << name
      @school[grade].sort!
    else
      # The only time we need to sort the hash
      # is when we had a new key to it. So we
      # do that here. This way we don't sort it
      # every time we call to_hash
      @school[grade] = [name]
      sort_hash
    end
  end

  def grade(grade_number)
    @school[grade_number] || []
  end

  # Helper method to sort the hash by key
  def sort_hash
    return_hash = {}
    @school.sort.map do | key, value |
      return_hash[key] = value
    end
    @school = return_hash
  end
end
