class Acronym
  def self.abbreviate(string)
    string_with_index = string.chars.each_with_index
    result = ""
    if string[0] == string[0].upcase && string[1] == string[1].upcase && string[2] == string[2].upcase
      return  "#{string[0]}#{string[1]}#{string[2]}"
    else
      string_with_index.map do |letter, index|
        result += letter if index == 0 || string[index-1] == " " || string[index-1] == "-" || letter == letter.upcase
      end
      result.upcase.chars.reject { |letter| letter == " " }.join.gsub(/[^a-zA-Z0-9]/, "")
    end
  end
end
