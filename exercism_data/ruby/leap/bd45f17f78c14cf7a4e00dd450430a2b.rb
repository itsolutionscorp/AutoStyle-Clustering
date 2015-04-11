module Year
  class << self
    def leap?(i)
      unless (i % 100 == 0)
        return (i % 4 == 0)
      else
        return (i % 400 == 0)
      end
    end
  end
end
