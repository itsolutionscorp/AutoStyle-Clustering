module Year
  DIV400 = ->(x) { (x % 400).zero? }
  DIV100 = ->(x) { (x % 100).zero? }
  DIV4   = ->(x) { (x % 4).zero? }

  def self.leap?(year)
    return false if DIV4.(year) && DIV100.(year) && !DIV400.(year)
    return true if DIV4.(year) && DIV100.(year) && DIV400.(year)
    return true if DIV4.(year) && !(DIV4.(year) && DIV100.(year))
  end
end
