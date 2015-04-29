class Year
def initialize(year)
  @year = year
end

def leap?
  Date.leap?(@year)
end

end
