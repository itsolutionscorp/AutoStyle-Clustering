class Year
  def self.leap?(jahre)
    if(jahre % 4 == 0 and jahre % 100 != 0) or (jahre % 100 == 0 and jahre % 400 == 0)
      "OK"
    end
  end
end
