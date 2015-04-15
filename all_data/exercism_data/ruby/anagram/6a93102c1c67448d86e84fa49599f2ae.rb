class Anagram < String
  # Slightly wary of overriding 'match' since it's already
  # defined on String, but I like how this reads
  # Fairly easy to change to < Struct.new(:base)
  def match(candidates)
    candidates.select do |candidate|
      Anagram.new(candidate).sort == self.sort
    end
  end

protected
  def sort
    split("").sort.join
  end
end
