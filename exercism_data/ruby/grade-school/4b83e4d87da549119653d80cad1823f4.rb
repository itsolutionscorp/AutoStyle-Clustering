class School
  
  def initialize
    @db = {}
    @db.extend(SortedKeys)
  end

  def add(name, grade)
    @db[grade] = [] if @db[grade].nil?
    @db[grade].push(name)
    @db[grade] = @db[grade].sort
  end

  def to_hash
    @db.to_hash
  end

  def grade(g)
    (@db[g] || []).sort
  end

  module SortedKeys
    def keys
      super.sort
    end
  end

end
o
