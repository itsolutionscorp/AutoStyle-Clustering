class School
  def initialize
    @school_hash = {}
  end
  def add(name, grade)
      if @school_hash[grade].nil?
        @school_hash[grade] = [name]
      else
        @school_hash[grade] << name
        @school_hash[grade].sort!
      end
  end
  def grade(grade)
    @school_hash[grade].nil? ? [] : @school_hash[grade]
  end
  def to_hash
    @school_hash.to_a.sort.to_h
  end
end
