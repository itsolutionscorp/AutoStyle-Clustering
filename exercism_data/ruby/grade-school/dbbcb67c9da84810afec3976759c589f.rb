class School
    def initialize
        @db = {}
    end

    def db
        @db
    end

    def add(student, grade)
        @db[grade] ||= []
        @db[grade].push student
    end

    def grade(classroom)
        @db[classroom] || []
    end

    def sort
        sorted_db = {}
        sorted_keys = @db.keys.sort
        
        sorted_keys.each do |grade|
            sorted_db[grade] = @db[grade].sort
        end

        sorted_db
    end
end
