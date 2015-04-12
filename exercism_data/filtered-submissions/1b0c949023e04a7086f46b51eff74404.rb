def compute(array1='', array2='')
    bytes1, bytes2, index = array1.bytes, array2.bytes, -1
    bytes1.count{|entry| index+=1; entry != bytes2[index] }
  end