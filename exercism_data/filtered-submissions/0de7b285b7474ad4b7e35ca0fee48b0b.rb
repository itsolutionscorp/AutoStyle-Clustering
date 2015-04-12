class Hamming
  def compute(s, t)
    @not_match = Array.new
    s.split('').each_with_index do |item, index|
      @not_match << item  if item != t.split('')[index]
    end
    @not_match.length
  end
end
