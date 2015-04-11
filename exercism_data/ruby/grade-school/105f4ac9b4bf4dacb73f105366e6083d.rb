class School
  def initialize
    @db = Hash.new { |hsh, key| hsh[key] = [] }
  end

  def add(name, year)
    @db[year] << name
  end

  def grade(year)
    @db[year].sort
  end

  def to_hash
    sorted = @db.sort_by do |year, _|
      year
    end.map do |x|
      [x.first, x.last.sort]
    end

    Hash[sorted]
  end
end
