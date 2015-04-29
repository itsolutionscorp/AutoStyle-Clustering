class School

  def initialize
    @roster = Hash.new { |k, h| k[h] = [] }
  end

  def add(name, grade)
    @roster[grade] << name
  end

  def to_hash
    school_roster = Hash.new
    @roster.keys.sort.each do |key|
      school_roster[key] = grade(key)
    end
    school_roster
  end

  def grade(grade)
    @roster[grade].sort
  end

end
