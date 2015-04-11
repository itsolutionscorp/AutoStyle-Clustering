class Atbash
  def Atbash.encode (str)
    str = str.downcase.gsub(/[^0-9a-z]/, "")
    str.chars.map{|c|
      /[a-z]/ =~ c ? (219 - c.ord).chr : c
    }.each_slice(5).map(&:join).join(" ")
  end
end
