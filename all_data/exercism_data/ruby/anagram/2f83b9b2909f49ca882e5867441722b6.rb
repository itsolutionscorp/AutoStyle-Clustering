class Anagram < String
  def match strings
    strings.select do |string|
      next if string.downcase == self.downcase
      sort(string) == sort(self)
    end
  end

  private
    def sort string
      string.downcase.chars.sort.join
    end
end
