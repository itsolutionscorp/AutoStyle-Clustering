module Eventlog
  refine Array do
    def to_hash_db
      self.each_with_object({}) do |event, out|
        out[event.grade] ||= []
        out[event.grade] << event.name
      end
    end
  end
end

Addition = Struct.new(:grade, :name) do
  def <=>(other)
    self.grade <=> other.grade ||
      self.name <=> other.name
  end
end

class School
  using Eventlog

  def initialize
    @eventlog = []
  end

  def add name, grade
    @eventlog << Addition.new(grade, name)
  end

  def grade level
    @eventlog.select { |event| event.grade == level }
             .map(&:name)
  end

  def grades
    @eventlog.map(&:grade).sort.uniq
  end

  def db
    @eventlog.to_hash_db
  end

  def sort
    @eventlog.sort.to_hash_db
  end
end
