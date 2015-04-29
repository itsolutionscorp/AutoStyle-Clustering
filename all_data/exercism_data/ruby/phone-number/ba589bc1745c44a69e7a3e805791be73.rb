class Phone
  def initialize(number)
    @number = clean(digits number)
  end
  
  def area_code
    sprintf "%03d", a2i(@number[0,3])
  end
  
  def number
    sprintf "%010d", a2i(@number)
  end
  
  def to_s
    sprintf "(%s) %03d-%04d", area_code, 
      a2i(@number[3,3]), a2i(@number[6,4])
  end
  
  private
  
  def clean(ds)
    if ((ds.length == 11) && (ds[0] == 1))
      ds.drop(1)
    elsif (ds.length == 10)
      ds
    else
      [0] * 10
    end
  end
  
  def digits(s)
    s.scan(/\d/).map{|c| c.to_i}
  end
  
  def a2i(ds)
    ds.join.to_i
  end
end
