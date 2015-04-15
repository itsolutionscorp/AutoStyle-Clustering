class School
  def initialize
    @hash = {}
  end

  def to_hash
    sorted_hash = {}
    @hash.keys.sort.each do |k|
      sorted_hash[k] = @hash[k].sort
    end
    sorted_hash
  end

  def add(name, grade)
    if @hash[grade] 
      @hash[grade].push(name)
    else
      @hash[grade] = [name]
    end
  end

  def grade(n)
    @hash[n] ? @hash[n].sort : []
  end
end
