class Hamming

  def self.compute(search_token, search_against)
    mod_search = search_against[0, search_token.length].chars
    mod_token = search_token[0,search_against.length].chars

    result = 0
    mod_token.each_with_index do |x,index|
      result += 1 if x != mod_search[index]
    end
    result
  end
end
