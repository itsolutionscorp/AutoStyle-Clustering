class School
  attr_reader :db
  def initialize
    @db = Hash.new { |hash, grade| hash[grade] = [] }
  end

  def add (name, grade)
   @db[grade] << name
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    sorted_hash = {}
    @db.keys.sort.each do |key|
      sorted_hash[key] = @db[key].sort
    end
    sorted_hash
  end
end
