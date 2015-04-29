class School

  attr_reader :db

  def initialize
    @db = Hash.new { |hash, key| hash[key] = [] }
  end

  def add(kid, grade)
    @db[grade] << kid
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    @db = @db.sort.each_with_object({}) do |(grade, kids), sorted_db|
      sorted_db[grade] = kids.sort
    end
  end

end
