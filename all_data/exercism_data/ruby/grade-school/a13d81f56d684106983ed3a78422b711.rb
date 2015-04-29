class School

  attr_reader :db

  def initialize
    @db = {}
  end

  def add(kid, grade)
    (@db[grade] ||= []) << kid
  end

  def grade(grade)
    @db.fetch(grade) { [] }
  end

  def sort
    @db = @db.sort.each_with_object({}) do |(grade, kids), sorted_db|
      sorted_db[grade] = kids.sort
    end
  end

end
