require "prime"

class Prime

  class << self
    def nth(value)
      raise ArgumentError if value < 1
      Prime.first(value).last
    end
  end
end
