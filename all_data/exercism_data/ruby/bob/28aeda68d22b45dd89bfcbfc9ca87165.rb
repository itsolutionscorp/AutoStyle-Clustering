class Bob

  def hey(prompt)
   # prompt = prompt.gsub(/\d/, "Dd")
    if prompt[-1] == "?" && prompt.upcase != prompt.gsub(/\d/, "")
      "Sure."
    elsif prompt.upcase.gsub(/\d/, "") == prompt.gsub(/\d/, "") && prompt.gsub(/\s+/, "").length != 0 && prompt.gsub(/\d/,"").gsub(/\,/,"").gsub(/\ /,"").length != 0
      "Woah, chill out!"
    elsif prompt.gsub(/\s+/, "").length == 0
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
