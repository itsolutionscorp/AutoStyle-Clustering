class Phrase
  def initialize(data)
    @data = data
  end

  def word_count
    data = @data.gsub(/[^a-z0-9 ]/i, ' ').downcase.split(/ +/)
    count = {}
    data.each do |d|
      if count[d] == nil
        count[d] = 1
      else
        count[d] += 1
      end
    end
    return count
  end
end
