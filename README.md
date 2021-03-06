# Fileshaft
Program that helping me to manage pictures in archive

## Requirements
Program require JRE 1.8 (Java SE 8)

## Running
Download stable release from "Releases" page.

Type in terminal:
```shell
java -jar ./fileshaft <?command> <?path>
```

## Commands
All commands works with pictures. 
Before renaming pictures, program makes one step changelog

### Mess
Rename to unique hash string.

#### Usage
```shell
fileshaft mess <?path>
```

#### Example
Picture `sorrow.jpg` becomes `1cc7248a-e21f.jpg`

### Time
Rename timestamped pictures with specific pattern (`yyyy-mm-dd hh_mm_ss`)

#### Usage
```shell
fileshaft time <?path>
```

#### Example
Picture `IMG_2000_01_01--12_12_40.jpg` becomes `2000-01-01 12_12_40.jpg`

### Revert
Reverting last changes (.fileshaft.json should be present in current path)

#### Usage
```shell
fileshaft revert
```

