class Fixnum
  def to_roman
    roman = {
        1 => 'I',
        2 => 'II',
        3 => 'III',
        4 => 'IV',
        5 => 'V',
        6 => 'VI',
        7 => 'VII',
        8 => 'VIII',
        9 => 'IX',
        10 => 'X',
        40 => 'XL',
        50 => 'L',
        90 => 'XC',
        100 => 'C',
        400 => 'CD',
        500 => 'D',
        900 => 'CM',
        1000 => 'M'
    }
    arr = self.to_s.split('').map(&:to_i)
    str = []
    if arr.size.equal?(1)
     str << roman[arr[0]]
    elsif arr.size.equal?(2)
        arr[0].times{ str << roman[10]} if arr[0] <= 3
        r5 = arr[0] - 5
        str << roman[40] if arr[0].equal?(4)
        str << roman[50] if arr[0].equal?(5)
        str << roman[50] && r5.times{ str << roman[10]} if (arr[0] >= 6 && arr[0] <= 8)
        str << roman[90] if arr[0].equal?(9)
        str << roman[arr[1]]
    elsif arr.size.equal?(3)
        r500 = arr[0] - 5
        r50 = arr[1] - 5
        arr[0].times{ str << roman[100]} if arr[0] <= 3
        str << roman[400] if arr[0].equal?(4)
        str << roman[500] if arr[0].equal?(5)
        str << roman[500] && r500.times{ str << roman[10]} if (arr[0] >= 6 && arr[0] <= 8)
        str << roman[900] if arr[0].equal?(9)
        arr[1].times{ str << roman[10]} if arr[1] <= 3
        str << roman[40] if arr[1].equal?(4)
        str << roman[50] if arr[1].equal?(5)
        str << roman[50] && r50.times{ str << roman[10]} if (arr[1] >= 6 && arr[1] <= 8)
        str << roman[90] if arr[1].equal?(9)
        str << roman[arr[2]]
    elsif arr.size.equal?(4)
        arr[0].times{ str << roman[1000]} if arr[0] <= 3
        r500 = arr[1] - 5
        r50 = arr[2] - 5
        arr[1].times{ str << roman[100]} if arr[1] <= 3
        str << roman[400] if arr[1].equal?(4)
        str << roman[500] if arr[1].equal?(5)
        str << roman[500] && r500.times{ str << roman[10]} if (arr[1] >= 6 && arr[1] <= 8)
        str << roman[900] if arr[1].equal?(9)
        arr[2].times{ str << roman[10]} if arr[2] <= 3
        str << roman[40] if arr[2].equal?(4)
        str << roman[50] if arr[2].equal?(5)
        str << roman[50] && r50.times{ str << roman[10]} if (arr[2] >= 6 && arr[2] <= 8)
        str << roman[90] if arr[2].equal?(9)
        str << roman[arr[3]]
    end
    str.join
  end
end
