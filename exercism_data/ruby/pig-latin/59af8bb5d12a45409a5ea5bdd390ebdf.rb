class PigLatin

  def self.translate(text)
    if text.include?(" ")
      results = []
      text.split(" ").map do |word|
        results << translator(word)
      end
      results.join(" ")
    else
      translator(text)
    end
  end

  def self.translator(text)

    test_section = text[0..2]

    if test_section[0..1] == "xr" || test_section[0..1] == "yt"
      text + "ay"
    elsif ["a", "e", "i", "o", "u"].include?(test_section[0])
      text + "ay"
    elsif test_section == "thr" || test_section == "sch"
      text.slice(3..-1) + test_section + "ay"
    elsif test_section[1..2] == "qu"
      text.slice(3..-1) + test_section + "ay"
    elsif test_section[0..1] == "ch" || test_section[0..1] == "th" || test_section[0..1] == "qu" 
      text.slice(2..-1) + test_section[0..1] + "ay"
    else
      text.slice(1..-1) + test_section[0]+ "ay"
    end
  end
