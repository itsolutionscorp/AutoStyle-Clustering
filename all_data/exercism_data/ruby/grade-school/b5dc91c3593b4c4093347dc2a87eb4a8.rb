class School

  def initialize
    @hash = {}
  end

  def to_hash
    Hash[@hash.sort]
  end

  def add(name, grade)
    unless @hash.has_key? grade
      @hash[grade] = [name]
    else
      names_array = @hash[grade]
      names_array << name
      @hash[grade] = names_array.sort
    end
  end

  def grade(grade)
    if @hash.has_key? grade
      return @hash[grade].sort
    else
      return []
    end
  end

end
