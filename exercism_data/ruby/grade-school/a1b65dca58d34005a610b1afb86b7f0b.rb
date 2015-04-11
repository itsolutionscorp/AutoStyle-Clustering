class School
  def initialize
    @db = {}
  end

  def add(name, grade)
    @db[grade] = [] unless @db[grade]
    @db[grade] << name
    @db[grade].sort!
  end

  def grade(num)
    if @db[num]
      @db[num]
    else
      []
    end
  end

  def to_hash
    Hash[@db.sort]
  end

end
