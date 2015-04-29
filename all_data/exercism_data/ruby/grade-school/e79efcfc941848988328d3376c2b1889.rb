class School
  attr_reader :db

  def initialize
    @db = Hash.new([])
  end

  def add(name, grade)
    @db[grade] += [name]
  end


  def grade(year)
   @db[year]
  end

  def sort
    Hash[sort_grades.zip(sort_names)]
  end

  def sort_names
    sort_grades.map do |k|
      @db[k].sort
    end
  end

  def sort_grades
    @db.keys.sort
  end
end
