class School

  def initialize
    @db = {}
  end

  def add(student, grade)
    if cohort = db[grade]
       after = cohort.bsearch { |classmate| classmate < student }
       i = cohort.index(after) || cohort.length
       cohort.insert(i - 1, student)
    else
      db[grade] = [student]
    end
  end

  def grade(cohort)
    Array(db[cohort])
  end

  def to_hash
    Hash[db.sort_by { |grade, roster| grade }]
  end

  private

  attr_reader :db

end
