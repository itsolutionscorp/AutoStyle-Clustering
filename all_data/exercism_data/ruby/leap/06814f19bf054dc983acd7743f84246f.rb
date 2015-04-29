module Year
  class << self
    def leap?(i)
      unless (i % 100).zero?
        return (i % 4).zero?
      else
        return (i % 400).zero?
      end
    end
  end
end
