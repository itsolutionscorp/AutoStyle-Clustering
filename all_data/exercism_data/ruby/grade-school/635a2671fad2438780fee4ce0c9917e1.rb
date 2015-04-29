class School
  
  def initialize
    @school = Hash.new {|hash, key| hash[key] = []}
  end

  def add(name, grade)
    @school[grade] << name
  end

  def grade(year)
    @school[year]
  end

  def sort
    copy_grades(@school.keys.sort, &sorted)
  end

  def db
    copy_grades(@school.keys, &cloned)
  end

  private

  def copy_grades(grades, &block)
    grades.each_with_object({}) do |year, result|
      result[year] = yield(grade(year))
    end
  end

  def sorted
    ->(value) {value.sort}
  end

  def cloned
    ->(value) {value.clone}
  end
end
