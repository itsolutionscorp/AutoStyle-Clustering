class School

  # can't figure out how to get the array values in the hash to be sorted. help?

  def initialize
    @students_hash = {}
  end

  def to_hash
    Hash[@students_hash.sort]
  end

  def add(name, grade)
    if @students_hash[grade] == nil
      @students_hash[grade] = [name]
    else
      @students_hash[grade] += [name]
    end
  end

  def grade(grade)
    @students_hash[grade] ||= []
  end

end
